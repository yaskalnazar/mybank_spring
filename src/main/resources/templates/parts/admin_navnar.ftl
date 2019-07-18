<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <form class="form-inline my-2 my-lg-0">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/admin/home">Home<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/account">My account</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/all_users">All users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/deposit_requests">Deposit requests</a>
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