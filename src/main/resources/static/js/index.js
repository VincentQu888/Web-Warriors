document.getElementById('fancy');
const adjectives = ['strongest', 'smartest', 'best'];
let index = -1;

function updateText() {
    let fancy = document.getElementById('fancy');
    fancy.style.opacity = 0.25;

    setTimeout(() => {
        index = (index+1) % adjectives.length; 
        fancy.innerText = adjectives[index]; 
        fancy.style.opacity = 1;
        if(fancy.innerText === 'strongest') fancy.style.color = '#fb9c36';
        else if(fancy.innerText === 'smartest') fancy.style.color = '#fb36da';
        else {
            fancy.style.background = 'linear-gradient(to right, #3688fb, #36d7fb)';
            fancy.style.backgroundClip = 'text';
            fancy.style.color = 'transparent';
        }
    }, 300)
}


let parentElement = document.getElementById('line');
let children = parentElement.children;
let levelCount = document.getElementById('levelNum').innerText;

for(let i = 0; i < children.length; i++) {
    if(i+1 <= parseInt(levelCount, 10)){
        let child = children[i];
        child.style.backgroundColor = '#fb9c36';
    }
}


updateText();
setInterval(updateText, 2500);



const rankDescriptions = [
    "Warriors learn about the fundamentals of websites, and how they work. They also learn how to add text to their websites.", 
    "At this rank, students learn to add different types of text. They use titles and different sized headings to structure their websites properly.",
    "Students learn how to add visuals to their websites. They learn how to add an image, and also create an alternate text for it.",
    "Once students hit the Captain rank, they start learning how to style their websites. They will learn how to change the text and background color of their websites.",
    "Students learn about divs and how to create a structured website. Also, students learn how to style these divs using CSS.",
    "At the final rank, students learn how they can align all their content in a row using flex, proving their mastery of HTML and CSS."
]
const levelNum = document.getElementById('levelNum').innerText;
document.getElementById("descriptionForRank").innerText = rankDescriptions[levelNum-1]