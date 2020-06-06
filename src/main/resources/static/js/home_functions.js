window.onload = function() {
    var buttons = document.getElementsByClassName('price_button');

    for (var i = 0; i < buttons.length; i++) {
        buttons[i].onclick = function(event) {
            var attr = this.getAttribute('id');
            attrArray = attr.split(' ');
            var id = attrArray[0];
            var storageStr = localStorage.getItem(id);

            if (storageStr == null) {
                var str = this.getAttribute('id');
                var array = str.split(' ');
                array[array.length] = 1;
                str = '';
                for (var j = 0; j < array.length - 1; j++) {
                    str += array[j] + ' ';
                }
                str += array[array.length - 1];
                localStorage.setItem(id, str);
            } else {
                var str = storageStr;
                var array = str.split(' ');
                array[array.length - 1] = Number(array[array.length - 1]) + 1;
                str = '';
                for (var j = 0; j < array.length - 1; j++) {
                    str += array[j] + ' ';
                }
                str += array[array.length - 1];
                localStorage.setItem(id, str);
            }

            var popup = document.getElementById('popup');
            popupClose = document.getElementById('close');
            popup.style.display="block";
            popupClose.onclick = function () {
                popup.style.display="none";
            };
        }
    }
};

window.onscroll = function() {
    var img = document.getElementById('home_img');
    img.style.opacity = 1 - document.documentElement.scrollTop * 0.0015;
}