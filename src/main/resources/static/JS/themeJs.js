var isWhiteText = localStorage.getItem('isWhiteText') === 'true';

function setTheme(isWhite) {
    var textElements = document.querySelectorAll('body :not(input, textarea, label, .dynamic__button)');
    var header = document.querySelector('header');
    var body = document.querySelector('body');
    var footer = document.querySelector('footer');
    var themeSwitcher = document.getElementById('theme-switcher'); // Получаем элемент переключения темы

    if (isWhite) {
        textElements.forEach(function(element) {
            element.style.color = '#000';
        });
        header.style.backgroundColor  = '#fff';
        body.style.backgroundColor  = '#fff';
        footer.style.backgroundColor  = '#fff';
        themeSwitcher.src = 'image/moon.svg';
    } else {
        textElements.forEach(function(element) {
            element.style.color = '#fff';
        });
        header.style.backgroundColor  = '#333';
        body.style.backgroundColor  = '#222';
        footer.style.backgroundColor  = '#333';
        themeSwitcher.src = 'image/sun.svg';
    }
}


setTheme(isWhiteText);

function toggleTheme() {
    isWhiteText = !isWhiteText;
    localStorage.setItem('isWhiteText', isWhiteText);
    setTheme(isWhiteText);
}
