const userHtml = document.getElementById('userHtml');
const userCss = document.getElementById('userCss');
const iframe = document.getElementById('preview').contentWindow.document;


let htmlEditor = CodeMirror.fromTextArea(document.getElementById('userHtml'), {
    mode: 'htmlmixed',
    lineNumbers: true,
    theme: 'monokai',
    tabSize: 5,
});


let cssEditor = CodeMirror.fromTextArea(document.getElementById('userCss'), {
    mode: 'css',
    lineNumbers: true,
    theme: 'monokai',
    tabSize: 5,
});

function updateIframe() {
    let htmlCode = htmlEditor.getValue();
    let cssCode = "<style>" + cssEditor.getValue() + "</style>";

    let iframe = document.getElementById('preview');
    let iframeDocument = iframe.contentDocument || iframe.contentWindow.document;
    
    const code = htmlCode + cssCode;
    iframeDocument.open();
    iframeDocument.write(code);
    iframeDocument.close();
}

htmlEditor.on('change', updateIframe);  
cssEditor.on('change', updateIframe);




let currentIndex = 0;

const texts1 = [
    "Welcome to the Web Warriors Arena! In the first stage of the arena, you're going to learn about websites and the layout of the Web Warriors Platform! Pass this level to be promoted to the rank of Soldier.", 
    "First off, what are websites? You can think of websites as a book, and the different sites are like pages of the book. In the top coding block, we have the HTML code. You can think of HTML as the words and content in the book, it makes up the content on the website!", 
    "Below the HTML, we have the CSS coding space. You can think of CSS as the artistic looks and styling of the book. Code placed in the CSS block changes how the content in the HTML block looks!", 
    "Over here on the left side, we have the submit button. When you think your code has met all the requirments, you can click submit to see if your code passed! If it passed, you can move on to the next level. If you fail, you can continue trying again!", 
    "For the first level, let's try adding some text to our website! Click into the HTML block and try typing something. You should see this appear on the display screen!",
    "Requirements for Level 1: \n - Add some text"
];
const texts2 = [
    "For level 2, we're gonna explore creating different types of text in our website!", 
    "For each new type of text we make, we are going to create tags for them. Each text has an opening tag and closing tag. These tags tell the computer what you want to write. Think of them as trapping the text you want to print. An example of opening and closing tags are <title> and </title>. The closing tag always has a / after the initial <.", 
    "The first type of text we will look at is different types of headings. Headings are like chapter titles in a book. They help organize the information on your webpage. There are different types of headings: h1, h2, and h3. h1 is the biggest heading, h2 is second biggest, and h3 is the smallest heading. An example of the tags for a heading is <h1> and </h1>. Try adding a heading of any size and naming it something!",
    "Next, we are going to learn about the paragraph text type. The paragraph text type is like the rest of the words in the book. They make up majority of the text, and give the readers information. The tags for a paragraph text is <p> and </p>. Try adding a paragraph text and write something creative inside!", 
    "Requirements for Level 2: \n Add a heading \n Add a paragraph"
];
const texts3 = [
    "In level 3, we're gonna look at how to import images into our websites! Images are an important part of a website because they provide a visual for the audience to see. Think of this as pictures in a book that help you see what is going on.", 
    "Let's first start by adding a heading for the image we are going to add! Remeber to use the correct tags <h1> and </h1>.", 
    "Next, let's try adding an image! We are going to use an <img> tag. You might have noticed that we only have 1 tag, and not 2. Instead of putting the information between the opening and closing tag, we are going to put it in the opening tag. For example: <img *insert information*>. What information are we putting inside the tag? We are going to put 2 elements, one called src which is a link to an image, and the other is a text that is displayed if the image is unavailable. Start by just adding the tag!", 
    "The first step is to find an image. Go to google and find an image you want to use. Right click on it and click copy image address. Then, inside your tag, add src=your_url. Next, add a space and another element alt=alternate_text. Think of an alternate text to use that will be displayed if the image can't be loaded!", 
    "Requirements for Level 3: \n Add a heading \n Add an image \n Add an alternate text to the image"
];

let texts = texts1
const level = document.getElementById('invis').innerText;
console.log(level);
if(level == 1) {
    texts = texts1;
} else if(level == 2) {
    texts = texts2;
} else if(level == 3) {
    texts = texts3;
}

function updateText() {
    document.getElementById('text-content').innerText = texts[currentIndex];
}

function nextText() {
    if (currentIndex < texts.length - 1) {
        currentIndex++;
        updateText();
    }
}

function prevText() {
    if (currentIndex > 0) {
        currentIndex--;
        updateText();
    }
}


function showPopup(){
    let popup = document.getElementById('popup');
    let bgCover = document.getElementById('bg-cover');
    popup.style.display = 'flex';
    bgCover.style.display = 'block';
}





updateText();