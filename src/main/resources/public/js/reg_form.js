angular.module("registration_form",[])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.auth = {};
        let resultMessageEl = document.getElementById('resultMessage');
        let exampleInputFirstNameEl = document.getElementById('exampleInputFirstName');
        let exampleInputLastNameEl = document.getElementById('exampleInputLastName');
        let exampleInputEmailEl = document.getElementById('exampleInputEmail');
        let exampleInputPasswordNameEl = document.getElementById('exampleInputPassword');
        let inputFirstNameLabel = document.getElementById('inputFirstNameLabel');
        let inputLastNameLabel = document.getElementById('inputLastNameLabel');
        let inputEmailLabel = document.getElementById('inputEmailLabel');
        let inputPasswordLabel = document.getElementById('inputPasswordLabel');
        exampleInputFirstNameEl.addEventListener('input', () => {
            inputFirstNameLabel.style.color = 'black';
            inputLastNameLabel.style.color = 'black';
            inputEmailLabel.style.color = 'black';
            inputPasswordLabel.style.color = 'black';
            $scope.message = '';
        });
        $scope.sendForm = function(auth){
            $http({
                method: "POST",
                url: "/api/reg_form",
                data: $.param(auth),
                headers: { "Content-Type" : "application/x-www-form-urlencoded" }
            }).then(
                (data) => {
                    resultMessageEl.style.color = 'green';
                    $scope.message = 'Успешно зарегистрирован';
                    exampleInputFirstNameEl.value = '';
                    exampleInputLastNameEl.value = '';
                    exampleInputEmailEl.value = '';
                    exampleInputPasswordNameEl.value = '';
                },
                (error) => {
                    console.log(error.data)
                    //resultMessageEl.style.color = 'red';
                    exampleInputEmailEl.style.color = 'red';
                    exampleInputPasswordNameEl.value = '';
                    //$scope.message = error.data.message;
                    window.alert(error.data.message);
                }
            );
        }
    });