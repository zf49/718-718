window.addEventListener("load", function () {
    const time = document.getElementById("countdown");
    function countdown() { time.innerText = (parseInt(time.innerText) - 1) + ""; }
    setInterval(countdown, 1000);
})