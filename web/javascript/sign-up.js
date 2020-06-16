window.addEventListener('load', () => {
    let usernameInput = document.getElementById('username');
    let usernameTakenLabel = document.getElementById('username-taken');
    let passwordInput = document.getElementById('password');
    let confirmPasswordInput = document.getElementById('confirmPassword');
    let passwordsDontMatchLabel = document.getElementById('password-dont-match');
    let submitButton = document.getElementById('submit');

    let usernameAvailable = true;
    let passwordMatch = true;

    usernameInput.addEventListener('input', () => {
        console.log(usernameInput.value);
    });

    passwordInput.addEventListener('input', () => {
        updatePasswordMatch();
    });

    confirmPasswordInput.addEventListener('input', () => {
        updatePasswordMatch();
    });

    function updatePasswordMatch() {
        passwordMatch = passwordInput.value === confirmPasswordInput.value;
        setLabelShow(passwordsDontMatchLabel, !passwordMatch);
        updateSubmitEnabled();
    }

    function updateSubmitEnabled() {
        submitButton.disabled = !(usernameAvailable && passwordMatch);
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