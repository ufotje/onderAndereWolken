package be.intec.repositories.implementation;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CommonSpecification<T> {

    private CommonSpecification() {
        super();
    }

    public static <T> Specification<T> filterVisibilityAndOptionalWhere(Map<String, String> readCriteria, Class<T> objectClass) {
        boolean show = (readCriteria.containsKey("showAll")) ? true : false;
        String whereEquals = readCriteria.containsKey("whereTextEquals") ? readCriteria.get("whereTextEquals") : null;
        return filterVisibilityAndOptionalWhere(whereEquals, show, objectClass);
    }

    public static <T> Specification<T> filterVisibilityAndOptionalWhere(String whereEquals, boolean showAll, Class<T> objectClass) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            String show = (showAll) ? "all" : "visible";

            Predicate condVisibility = visibility(cb, root, show);
            Predicate condWhereEquals = whereTextEquals(cb, root, whereEquals);


            predicates.add(condVisibility);
            if(condWhereEquals != null) { predicates.add(condWhereEquals); }


            return cb.and(predicates.toArray(new Predicate[] {}));
        };
    }

    public static <T> Specification<T> joinSpecifications(Specification<T> ... specifications) {
        return (root, query, cb) -> {
            if(specifications.length < 2) {
                throw new IllegalArgumentException("Not enough elements in list to execute a join");
            }
            Specification joinedSpec = joinSpecificationCouple(specifications[0], specifications[1]);
            for(Specification spec : Arrays.copyOfRange(specifications, 2, specifications.length)) {
                joinedSpec = joinSpecificationCouple(joinedSpec, spec);
            }
            return joinedSpec.toPredicate(root, query, cb);
        };
    }

    private static <T> Specification<T> joinSpecificationCouple(Specification<T> first, Specification<T> second) {
        return (root, query, cb) -> {
          return cb.and(first.toPredicate(root, query, cb), second.toPredicate(root, query, cb));
        };
    }

    /* This method is used to limit the search results and possibly sort them*/
    public static Pageable limitAndOrSort(Map<String, String> readCriteria) {
        int size = (readCriteria.containsKey("size") && readCriteria.get("size").matches("^\\d+$")) ? Integer.valueOf(readCriteria.get("size")) : Integer.MAX_VALUE;
        int page = (readCriteria.containsKey("page") && readCriteria.get("page").matches("^\\d+$")) ? Integer.valueOf(readCriteria.get("page")) : 0;
        String order = (readCriteria.containsKey("order")) ? readCriteria.get("order") : null;
        String orderDirection = (readCriteria.containsKey("orderDirection") && readCriteria.get("orderDirection").equalsIgnoreCase("desc")) ? "DESC" : "ASC";
        if(order != null) {
            return new PageRequest(page, size, sort(order, orderDirection));
        }
        return new PageRequest(page, size);
    }

    private static Sort sort(String order, String orderDirection) {
        return new Sort(Sort.Direction.valueOf(orderDirection), order);
    }

    /** This method provides a predicate that filters on visibility of the fields.
     * Default option is to show all, other two options are: visible (= only show visible fields) and hidden (= only show hidden fields)
     * This method requires the entity to have a boolean field named visible and a boolean field named deleted*/
    private static <T> Predicate visibility(CriteriaBuilder cb, Root<T> root, String visibilityOption) {
        Predicate predicate;
        switch (visibilityOption) {
            case "visible":
                predicate = cb.and(cb.equal(root.get("visible"), true), cb.equal(root.get("deleted"), false));
                break;
            case "hidden":
                predicate = cb.and(cb.equal(root.get("visible"), false), cb.equal(root.get("deleted"), false));
                break;
            default:
                predicate = cb.equal(root.get("deleted"), false);
                break;
        }
        return predicate;
    }

    /** This method provides a predicate that filters the query on a specific value
     * it's triggered by adding a param formed like ~paramName(whereEqual)~ ~DBcolumn~ ~separator(::)~ ~value~
     * example: ?whereEquals=title::This_is_a_title     */
    public static <T>Predicate whereTextEquals(CriteriaBuilder cb, Root<T> root, String fieldNameAndValue) {
        if(!StringUtils.hasText(fieldNameAndValue)) return null;
        String[] NameAndValue = fieldNameAndValue.split("::");
        if(NameAndValue.length == 2) {
            String fieldName = NameAndValue[0];
            String fieldValue = NameAndValue[1];
            return cb.equal(root.get(fieldName), fieldValue);
        }
        return null;
    }

    public static<T> Specification<T> whereFieldEquals(String fieldname, Object fieldvalue, Class<T> objectClass) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get(fieldname)), fieldvalue);
    }

    public static<T> Specification<T> searchTitleContainsIgnoreCase(Class<T> objectClass, String... searchTerms) {
        return (root, query, cb) -> {
            if(searchTerms.length < 2) {
                return searchTitleContainsIgnoreCase(objectClass, searchTerms[0]).toPredicate(root, query, cb);
            }
            Specification<T> specification = joinSpecificationCouple(searchTitleContainsIgnoreCase(objectClass, searchTerms[0]), searchTitleContainsIgnoreCase(objectClass, searchTerms[1]));
            if(searchTerms.length > 2) {
                for(int i = 2; i < searchTerms.length; i++){
                    specification = joinSpecificationCouple(specification, searchTitleContainsIgnoreCase(objectClass, searchTerms[i]));
                }
            }
            return specification.toPredicate(root, query, cb);
        };
    }

    /** This method checks if field title of an entity contains the given searchTerm*/
    public static<T> Specification<T> searchTitleContainsIgnoreCase(Class<T> objectClass, String searchTerm) {
        return (root, query, cb) -> {
            String containsLikePattern = getContainsLikePattern(searchTerm);
            return cb.like(cb.lower(root.<String>get("title")), containsLikePattern);
        };
    }

    private static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        }
        else {
            return "%" + searchTerm.toLowerCase() + "%";
        }
    }

}
