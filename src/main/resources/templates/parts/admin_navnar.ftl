<nav class="navbar navbar-light bg-light">
    <form class="form-inline">
        <input class="form-control mr-sm-2" type="search" placeholder=<@spring.message "navbar.search"/> aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><@spring.message "navbar.search"/></button>
    </form>
    Admin
    <span style="float: right">
        <button class="btn btn-secondary mr-2" onclick="window.location.href = '/logout';">
                    <@spring.message "logout"/>
         </button>
        <@spring.message "language"/>: <a href="?language=en">Eng</a> | <a href="?language=uk">Укр</a>
    </span>
</nav>