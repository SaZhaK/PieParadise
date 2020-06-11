document.getElementById('Meat_item').onclick = function() {
    if (this.checked) {
        document.getElementById('meat').style.opacity = 1;
        addTotal(document.getElementById('Meat').getAttribute('value'));
    } else {
        document.getElementById('meat').style.opacity = 0;
        subtractTotal(document.getElementById('Meat').getAttribute('value'));
    }
}

document.getElementById('Fish_item').onclick = function() {
    if (this.checked) {
        document.getElementById('fish').style.opacity = 1;
        addTotal(document.getElementById('Fish').getAttribute('value'));
    } else {
        document.getElementById('fish').style.opacity = 0;
        subtractTotal(document.getElementById('Fish').getAttribute('value'));
    }
}

document.getElementById('Egg_item').onclick = function() {
    if (this.checked) {
        document.getElementById('egg').style.opacity = 1;
        addTotal(document.getElementById('Egg').getAttribute('value'));
    } else {
        document.getElementById('egg').style.opacity = 0;
        subtractTotal(document.getElementById('Egg').getAttribute('value'));
    }
}

document.getElementById('Potatoes_item').onclick = function() {
    if (this.checked) {
        document.getElementById('potatoes').style.opacity = 1;
        addTotal(document.getElementById('Potatoes').getAttribute('value'));
    } else {
        document.getElementById('potatoes').style.opacity = 0;
        subtractTotal(document.getElementById('Potatoes').getAttribute('value'));
    }
}

document.getElementById('Cheese_item').onclick = function() {
    if (this.checked) {
        document.getElementById('cheese').style.opacity = 1;
        addTotal(document.getElementById('Cheese').getAttribute('value'));
    } else {
        document.getElementById('cheese').style.opacity = 0;
        subtractTotal(document.getElementById('Cheese').getAttribute('value'));
    }
}

document.getElementById('Cottage cheese_item').onclick = function() {
    if (this.checked) {
        document.getElementById('cottage_cheese').style.opacity = 1;
        addTotal(document.getElementById('Cottage cheese').getAttribute('value'));
    } else {
        document.getElementById('cottage_cheese').style.opacity = 0;
        subtractTotal(document.getElementById('Cottage cheese').getAttribute('value'));
    }
}

document.getElementById('Dill_item').onclick = function() {
    if (this.checked) {
        document.getElementById('dill').style.opacity = 1;
        addTotal(document.getElementById('Dill').getAttribute('value'));
    } else {
        document.getElementById('dill').style.opacity = 0;
        subtractTotal(document.getElementById('Dill').getAttribute('value'));
    }
}

document.getElementById('Species_item').onclick = function() {
    if (this.checked) {
        document.getElementById('species').style.opacity = 1;
        addTotal(document.getElementById('Species').getAttribute('value'));
    } else {
        document.getElementById('species').style.opacity = 0;
        subtractTotal(document.getElementById('Species').getAttribute('value'));
    }
}

document.getElementById('Apple_item').onclick = function() {
    if (this.checked) {
        document.getElementById('apple').style.opacity = 1;
        addTotal(document.getElementById('Apple').getAttribute('value'));
    } else {
        document.getElementById('apple').style.opacity = 0;
        subtractTotal(document.getElementById('Apple').getAttribute('value'));
    }
}

document.getElementById('Lemon_item').onclick = function() {
    if (this.checked) {
        document.getElementById('lemon').style.opacity = 1;
        addTotal(document.getElementById('Lemon').getAttribute('value'));
    } else {
        document.getElementById('lemon').style.opacity = 0;
        subtractTotal(document.getElementById('Lemon').getAttribute('value'));
    }
}

document.getElementById('Strawberry_item').onclick = function() {
    if (this.checked) {
        document.getElementById('strawberry').style.opacity = 1;
        addTotal(document.getElementById('Strawberry').getAttribute('value'));
    } else {
        document.getElementById('strawberry').style.opacity = 0;
        subtractTotal(document.getElementById('Strawberry').getAttribute('value'));
    }
}

document.getElementById('Cherry_item').onclick = function() {
    if (this.checked) {
        document.getElementById('cherry').style.opacity = 1;
        addTotal(document.getElementById('Cherry').getAttribute('value'));
    } else {
        document.getElementById('cherry').style.opacity = 0;
        subtractTotal(document.getElementById('Cherry').getAttribute('value'));
    }
}

document.getElementById('Raspberry_item').onclick = function() {
    if (this.checked) {
        document.getElementById('raspberry').style.opacity = 1;
        addTotal(document.getElementById('Raspberry').getAttribute('value'));
    } else {
        document.getElementById('raspberry').style.opacity = 0;
        subtractTotal(document.getElementById('Raspberry').getAttribute('value'));
    }
}

document.getElementById('create_button').onclick = function() {
    var ingredients = document.getElementsByClassName('ingredients');
    var str = '';

    for (var i = 0; i < ingredients.length; i++) {
        if (ingredients[i].checked) {
            var name = document.getElementById(ingredients[i].getAttribute('value'));
            str += ingredients[i].getAttribute('value') + ' ' + name.getAttribute('value') + '/';
        }
    }

    if (localStorage.getItem('id') == null) {
        localStorage.setItem('id', 2);
        localStorage.setItem('custom1', str);
    } else {
    var number = localStorage.getItem('id');
    localStorage.setItem('custom' + number, str);
    localStorage.setItem('id', Number(number) + 1);
    }
}

function addTotal(price) {
    var sum = document.getElementById('sum');
    sum.innerHTML = Number(sum.innerHTML.substring(0, sum.innerHTML.length - 3)) + Number(price) + ' rub';
}

function subtractTotal(price) {
    var sum = document.getElementById('sum');
    sum.innerHTML = Number(sum.innerHTML.substring(0, sum.innerHTML.length - 3)) - Number(price) + ' rub';
}