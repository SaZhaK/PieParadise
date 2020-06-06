var purchases = [];
var idx = 0;
var totalPrice = 0;

window.onload = function() {

    var container = document.getElementById('products_list');
    var res = '';

    for (var id = 1; id <= 8; id++) {
        if(localStorage.getItem(id) != null) {
            var str = localStorage.getItem(id);
            purchases[idx++] = str;

            var array = str.split(' ');
            str = '';
            var amount = array[array.length - 1];
            var price = array[array.length - 2];

            for (var i = 1; i < array.length - 2; i++) {
                str += array[i] + ' ';
            }
            res += '<div id="' + id + '" class="product_frame">'
                + '<div class="product_item">'
                + '<div class="product_text">'
                + 'x' + amount + ' ' + str
                + ' ....... ' + (amount * price) + ' rub' + '</div>'
                + '</div> <div class="delete_frame"> '
                + '<input class="delete_button" type="button" value="delete" onclick="myDelete(' + id + ')"> </div> </div>';

                totalPrice += Number(amount) * Number(price);
        }
    }

    var maxIdx = localStorage.getItem('id');

    for (var k = 1; k < Number(maxIdx); k++) {
        if (localStorage.getItem('custom' + k) != null) {
            var customStr = localStorage.getItem('custom' + k);
            purchases[idx++] = customStr;
            var customArray = customStr.split('/');
            customStr = '';
            var customPrice = 0;
            for (var i = 0; i < customArray.length - 1; i++) {
                var elements = customArray[i].split(' ');
                var name = '';
                for (var j = 0; j < elements.length - 1; j++) {
                    name += elements[j] + ' ';
                }
                customStr += name.substring(0, name.length - 1) + ', ';
                var price = elements[elements.length - 1];
                customPrice += Number(price);
            }
            res += '<div id="custom' + k + '" class="product_frame">'
                + '<div class="product_item">'
                + '<div class="product_text">'
                + 'x1 Custom: ' + customStr.substring(0, customStr.length - 2)
                + ' ....... ' + customPrice + ' rub' + '</div>'
                + '</div> <div class="delete_frame"> '
                + '<input class="delete_button" type="button" value="delete" onclick="customDelete(\'custom' + k + '\')"> </div> </div>';

                totalPrice += Number(customPrice);
        }
    }

    container.innerHTML = res;

    var sum = document.getElementById('sum');
    sum.innerHTML = Number(totalPrice);
}

function myDelete(id) {
    var str = localStorage.getItem(id);
    var array = str.split(' ');
    var amount = array[array.length - 1];
    var price = array[array.length - 2];

    var sum = document.getElementById('sum');
    sum.innerHTML = Number(sum.innerHTML) - Number(price * amount);

    var element = document.getElementById(id);
    element.parentNode.removeChild(element);
    localStorage.removeItem(id);
}

function customDelete(id) {
    var customStr = localStorage.getItem(id);
    var customArray = customStr.split('/');
    var customPrice = 0;
    for (var i = 0; i < customArray.length - 1; i++) {
        var elements = customArray[i].split(' ');
        var price = elements[elements.length - 1];
        customPrice += Number(price);
    }

    var sum = document.getElementById('sum');
    sum.innerHTML = Number(sum.innerHTML) - Number(customPrice);

    var element = document.getElementById(id);
    element.parentNode.removeChild(element);
    localStorage.removeItem(id);
}

document.getElementById('submit_button').onclick = function() {
    var name = document.getElementById('name').value;
    var lastName = document.getElementById('lastName').value;
    var address = document.getElementById('address').value;
    var mail = document.getElementById('mail').value;
    var phone = document.getElementById('phone').value;
    var cardNumber = document.getElementById('cardNumber').value;

    var phoneReg = new RegExp("\\+[0-9]{11}");
    var mailReg = new RegExp("[.]*@[.]*");
    var cardReg = new RegExp("[0-9]{16}");

    var amount = 0;
    for (var i = 1; i <= 8; i++) {
        if (localStorage.getItem(i) != null)  amount++;
        if (localStorage.getItem('custom' + i) != null) amount++;
    }

    if (amount == 0) {
        this.setAttribute('href', '##')
        document.getElementById('error').style.opacity = 1;
        document.getElementById('error').innerHTML = 'You have to choose at least 1 product';
    } else if (name == '') {
        this.setAttribute('href', '##')
        document.getElementById('error').style.opacity = 1;
    } else if (lastName == '') {
        this.setAttribute('href', '##')
        document.getElementById('error').style.opacity = 1;
    } else if (address == '') {
        this.setAttribute('href', '##')
        document.getElementById('error').style.opacity = 1;
    } else if ((mail == '') || !mailReg.test(mail)) {
        this.setAttribute('href', '##')
        document.getElementById('error').style.opacity = 1;
    } else if ((phone == '') || !phoneReg.test(phone)) {
        this.setAttribute('href', '##')
        document.getElementById('error').style.opacity = 1;
    } else if ((cardNumber == '') || !cardReg.test(cardNumber)) {
        this.setAttribute('href', '##')
        document.getElementById('error').style.opacity = 1;
    } else {
        this.setAttribute('href', '/');

        clearStorage();

        var order = {
            name: name,
            lastName: lastName,
            mail: mail,
            phone: phone,
            address: address,
            cardNumber: cardNumber,
            purchases: purchases
        };

        var response = fetch('/orders', {
            method: 'POST',
            headers: {'Content-Type': 'application/json;charset=utf-8'},
            body: JSON.stringify(order)
        })
    }
}

function clearStorage() {
    var maxIdx = localStorage.getItem('id');
    for (var i = 1; i <= maxIdx; i++) {
        localStorage.removeItem('custom' + i);
    }
    localStorage.removeItem('id');

    for (var i = 1; i <= 8; i++) {
        if (localStorage.getItem(i) != null) {
            localStorage.removeItem(i);
        }
    }
}