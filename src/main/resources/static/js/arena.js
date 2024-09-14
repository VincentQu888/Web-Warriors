const userHtml = document.getElementById('userHtml');
const userCss = document.getElementById('userCss');
const iframe = document.getElementById('preview').contentWindow.document;

//html auto update
userHtml.addEventListener('input', () => {
    const code = userHtml.value + "<head><style>" + userCss.value + "</style></head>";
    iframe.open();
    iframe.write(code);
    iframe.close();
});

//css auto update
userCss.addEventListener('input', () => {
    const code = userHtml.value + "<head><style>" + userCss.value + "</style></head>";
    iframe.open();
    iframe.write(code);
    iframe.close();
});