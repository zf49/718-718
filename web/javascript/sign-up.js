window.addEventListener('load', () => {
    const usernameInput = document.getElementById('username');
    const usernameTakenLabel = document.getElementById('username-taken');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirmPassword');
    const passwordsDontMatchLabel = document.getElementById('password-dont-match');
    const submitButton = document.getElementById('submit');

    let usernameAvailable = true;
    let passwordMatch = true;

    usernameInput.addEventListener('input', async () => {
        let username = usernameInput.value;
        let response = await fetch(`${servletContextPath}/username-available/${username}`);
        let json = await response.json();

        // skip if input has already changed to something else
        if (usernameInput.value !== json.username) {
            return;
        }

        updateUsernameAvailable(json.available);
    });

    passwordInput.addEventListener('input', () => {
        updatePasswordMatch();
    });

    confirmPasswordInput.addEventListener('input', () => {
        updatePasswordMatch();
    });

    function updateUsernameAvailable(available) {
        usernameAvailable = available;
        setLabelShow(usernameTakenLabel, !usernameAvailable);
        updateSubmitEnabled();
    }

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