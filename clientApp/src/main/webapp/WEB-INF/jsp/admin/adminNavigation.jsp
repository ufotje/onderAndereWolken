
<c:url var="logoutUrl" value="/logout"/>

<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand fontHeader" href="/admin">Onder andere wolken</a>
    </div>
    <!-- /.navbar-header -->
    <ul class="nav navbar-top-links navbar-right">

        <a href="${pageContext.request.contextPath}/" class="navbar-link" style="margin-right: 15px;">Startpagina</a>

        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="/">
                Mijn reizen<i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="${pageContext.request.contextPath}/trips" class="navbar-link"> Routes</a>
                </li>
                <li class="divider"></li>
                <li><a href="#" class="navbar-link"> Foto's</a>
                </li>
                <li class="divider"></li>
                <li><a href="${pageContext.request.contextPath}/verhalen" class="navbar-link"> Verhalen</a>
                </li>
                <li class="divider"></li>
                <li><a href="${pageContext.request.contextPath}/kijkjes" class="navbar-link"> Filmpjes</a>
                </li>
            </ul>
        </li>
        <a href="${pageContext.request.contextPath}/links" style="margin-right: 15px;">Links</a>
        <a href="${pageContext.request.contextPath}/gastenboek" style="margin-right: 15px;">Gastenboek</a>
        <a href="${pageContext.request.contextPath}/contact" style="margin-right: 15px;">Contact</a>
        <form action="/logout" method="post">
            <input type="submit" value="logout" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </ul>
    <!-- /.navbar-top-links -->

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">

                <li>
                    <a href="#"><i class="fa fa-suitcase fa-fw"></i> Trips<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/trips">Toon</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/trips/nieuw">Nieuw</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="#"><i class="fa fa-book fa-fw"></i> Verhalen<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/verhalen">Toon</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/verhalen/nieuw">Nieuw</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                     <a href="#"><i class="fa fa-picture-o"></i> Kiekjes<span class="fa arrow"></span></a>
                     <ul class="nav nav-second-level">
                         <li>
                            <a href="${pageContext.request.contextPath}/admin/pictures">Toon</a>
                          </li>
                           <li>
                            <a href="${pageContext.request.contextPath}/admin//pictures/add">Nieuw</a>
                         </li>
                      </ul>
                      <!-- /.nav-second-level -->
                  </li>
                <li>
                    <a href="#"><i class="fa fa-video-camera fa-fw"></i> Kijkjes<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/kijkjes">Toon</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/kijkjes/nieuw">Nieuw</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>

                 <li>

                    <a href="${pageContext.request.contextPath}/admin/allecontacten"><i class="fa fa-envelope"></i> Berichtjes</a>
                  </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/links"><i class="fa fa-link fa-fw"></i>
                        Links</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-map-marker"></i> Locaties<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/locaties">Toon</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/locaties/nieuw">Nieuw</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>