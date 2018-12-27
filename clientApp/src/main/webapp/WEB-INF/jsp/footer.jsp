<footer class="py-5 bg-dark">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 text-center">
                <p class="m-0 text-white">Auteursrecht &copy; Mega Mindies 2017</p>
            </div>
            <div class="col-lg-4 text-center">
            <a name="subscribepart"></a>
                <form method="post" action="${pageContext.request.contextPath}newsletter/subscribe/#subscribepart">
                    <input type="text" name="nieuwsbrief" placeholder="jan@voorbeeld.be"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" name="submit" value="Inschrijven nieuwsbrief"/>
                    <p style="color:red" >${message}</p>
                    <c:forEach items="${errors}" var="error">
                      <p style="color:red" > ${error.defaultMessage}<br/></p>
                    </c:forEach>
                </form>
            </div>
            <div class="col-lg-4 text-center">
                <a href="/admin">Admin pagina</a>
            </div>
        </div>
    </div>
</footer>
