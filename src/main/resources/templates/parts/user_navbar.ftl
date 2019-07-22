<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <form class="form-inline my-2 my-lg-0">
        <input class="form-control mr-sm-2" type="search" placeholder=<@spring.message "navbar.search"/> aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><@spring.message "navbar.search"/></button>
    </form>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/user/home"><@spring.message "navbar.home"/><span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user/account/all_accounts/"><@spring.message "navbar.user.my.accouts"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user/account/deposit/open/"><@spring.message "navbar.user.deposit.open"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user/account/credit/open/">Credit open</a>
            </li>
        </ul>
    </div>
    <span style="float: right">
        <button class="btn btn-secondary mr-2" onclick="window.location.href = '/logout';">
                    <@spring.message "logout"/>
         </button>
        <@spring.message "language"/>: <a href="?language=en">Eng</a> | <a href="?language=uk">Укр</a>
    </span>
</nav>