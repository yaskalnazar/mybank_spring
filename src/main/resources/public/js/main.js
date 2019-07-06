angular.module("login_form",[])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.auth = {};
        let exampleInputEmail1 = document.getElementById('exampleInputEmail1');
        let exampleInputPassword1 = document.getElementById('exampleInputPassword1');
        $scope.sendForm = function(auth){
            $http({
                method: "POST",
                url: "/login",
                data: $.param(auth),
                headers: { "Content-Type" : "application/x-www-form-urlencoded" }
            }).then(
                function(data) {
                    window.alert("Access is allowed");
                    window.location.replace("photo.jpg");
                },
                function(error) {
                    console.log(error.data);
                    exampleInputPassword1.value = '';
                    exampleInputEmail1.style.color = 'red';
                    window.alert(error.data.message);
                }
            );
        }
    });