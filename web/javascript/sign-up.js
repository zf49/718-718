window.addEventListener('load', () => {
    let usernameInput = document.getElementById('username');
    let usernameTakenLabel = document.getElementById('username-taken');
    let passwordInput = document.getElementById('password');
    let confirmPasswordInput = document.getElementById('confirmPassword');
    let passwordsDontMatchLabel = document.getElementById('password-dont-match');

    let passwordMatch = false;

    usernameInput.addEventListener('input', () => {
        console.log(usernameInput.value);
    });

    passwordInput.addEventListener('input', () => {
        console.log(passwordInput.value);
        updatePasswordMatch();
    });

    confirmPasswordInput.addEventListener('input', () => {
        console.log(confirmPasswordInput.value);
        updatePasswordMatch();
    });

    function updatePasswordMatch() {
        passwordMatch = passwordInput.value === confirmPasswordInput.value;
        setLabelShow(passwordsDontMatchLabel, !passwordMatch);
    }

    function setLabelShow(label, show) {
        if (show) {
            if (label.classList.contains('d-none')) {
                label.classList.remove('d-none');
            }
        } else {
            if (!label.classList.contains('d-none')) {
                label.classList.add('d-none');
            }
        }
    }
});