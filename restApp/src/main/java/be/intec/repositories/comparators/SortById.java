package be.intec.repositories.comparators;

import be.intec.entities.Picture;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class SortById implements Comparator<Picture>{

        @Override
        public int compare(Picture o1, Picture o2) {
            if (o1.getId() < o2.getId() ){
                return 1;
            }else if(o1.getId() == o2.getId()){
                return 0;
            }else{
                return -1;
            }
        }

}
