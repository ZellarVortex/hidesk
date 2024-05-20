var isWhiteText = localStorage.getItem('isWhiteText') === 'true';

function setTheme(isWhite) {
    var textElements = document.querySelectorAll('body :not(.ignore, input, textarea, label, .dynamic__button, .profile__info__block, .course__info, summary, table, td, tr, th)');
    var header = document.querySelector('header');
    var body = document.querySelector('body');
    var footer = document.querySelector('footer');
    var themeSwitcher = document.getElementById('theme-switcher'); // Получаем элемент переключения темы

    var profile = document.getElementById("profile");

    if (isWhite) {
        textElements.forEach(function(element) {
            element.style.color = '#000';
        });
        header.style.backgroundColor  = '#fff';
        body.style.backgroundColor  = '#fff';
        footer.style.backgroundColor  = '#f0f0f0';
        themeSwitcher.src = '/image/moon.svg';
        profile.src = '/image/profileBlack.svg';
    } else {
        textElements.forEach(function(element) {
            element.style.color = '#fff';
        });
        header.style.backgroundColor  = '#333';
        body.style.backgroundColor  = '#222';
        footer.style.backgroundColor  = '#333';
        themeSwitcher.src = '/image/sun.svg';
        profile.src = '/image/profileWhite.svg';
    }
}


setTheme(isWhiteText);

function toggleTheme() {
    isWhiteText = !isWhiteText;
    localStorage.setItem('isWhiteText', isWhiteText);
    setTheme(isWhiteText);
}
