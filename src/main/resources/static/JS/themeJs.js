var isWhiteText = localStorage.getItem('isWhiteText') === 'true';

// Функция для установки темы
function setTheme(isWhite) {
    // Получаем все элементы с текстом на странице, кроме input
    var textElements = document.querySelectorAll('body :not(input, textarea, label)');
    var header = document.querySelector('header');
    var body = document.querySelector('body');
    var footer = document.querySelector('footer');
    var a = document.querySelector('a');
    var h1 = document.querySelector('h1');

    // Устанавливаем цвета в зависимости от выбранной темы
    if (isWhite) {
        textElements.forEach(function(element) {
            element.style.color = '#000';
        });
        header.style.backgroundColor  = '#fff';
        body.style.backgroundColor  = '#fff';
        footer.style.backgroundColor  = '#fff';
        a.style.color = '#000';
        h1.style.color = '#000';
    } else {
        textElements.forEach(function(element) {
            element.style.color = '#fff';
        });
        header.style.backgroundColor  = '#333';
        body.style.backgroundColor  = '#222';
        footer.style.backgroundColor  = '#333';
        a.style.color = '#fff';
        h1.style.color = '#fff';
    }
}


// Устанавливаем тему при загрузке страницы
setTheme(isWhiteText);

// Функция для переключения темы
function toggleTheme() {
    // Инвертируем текущее состояние
    isWhiteText = !isWhiteText;
    // Сохраняем состояние темы в Local Storage
    localStorage.setItem('isWhiteText', isWhiteText);

    // Устанавливаем новую тему
    setTheme(isWhiteText);
}