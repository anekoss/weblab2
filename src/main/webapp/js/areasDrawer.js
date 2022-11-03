$(document).ready(function () {
    drawPicture();
    drawAllDots();
});

function drawPicture() {
    let canvas = document.getElementById("areas");
    let ctx = canvas.getContext("2d");
    let img = document.getElementById("areas_picture");
    let size = canvas.width;
    ctx.drawImage(img, 0, 0, size, size);
}

function drawDot(x, y, result) {
    let canvas = document.getElementById("areas");
    let ctx = canvas.getContext("2d");
    let radius = 3;
    let size = canvas.width;
    let factor = 0.1;
    x = x * size * factor + 0.5 * size - radius * 0.5;
    y = -y;
    y = y * size * factor + 0.5 * size - radius * 0.5;

    ctx.fillStyle = !result ? "#ff3333" : "#45f235";

    ctx.beginPath();
    ctx.arc(x, y, radius, 0, Math.PI * 2, false);
    ctx.closePath();
    ctx.fill();
}

function drawAllDots() {
    let rows = $("#response_body>tr");
    for (let i = 0; i < rows.length; i++) {
        let row = rows.eq(i);
        let fields = row.children();
        let result = fields.eq(5).text().trim() === 'true';
        let x = fields.eq(0).text();
        let y = fields.eq(1).text();
        let r = fields.eq(2).text();
        x /= r;
        x = x.toFixed(2);
        y /= r;
        y = y.toFixed(2);
        drawDot(x, y, result);
    }
}