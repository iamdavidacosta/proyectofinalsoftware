$(function() {
    // Cálculo Científico
    $('#multBtn').click(() => {
        const a = $('#a').val();
        const b = $('#b').val();
        $.ajax({
            url: '/mvc/calc/matrix',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ a: JSON.parse(a), b: JSON.parse(b) }),
            success: html => $('#multResult').html(html)
        });
    });
    $('#intBtn').click(() => {
        const lower = parseFloat($('#lower').val());
        const upper = parseFloat($('#upper').val());
        const subintervals = parseInt($('#sub').val());
        const threads = parseInt($('#threads').val());
        $.ajax({
            url: '/mvc/calc/integrate',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ lower, upper, subintervals, threads }),
            success: html => $('#intResult').html(html)
        });
    });

    // Gestión de Calificaciones
    $('#createGradeBtn').click(() => {
        $.post('/mvc/grades/create', $('#gradesForm').serialize())
            .done(html => $('#gradeResult').html(html));
    });
    $('#listGradesBtn').click(() => {
        $.get('/mvc/grades/list')
            .done(html => $('#gradeResult').html(html));
    });

    // Biblioteca
    $('#createBookBtn').click(() => {
        $.post('/mvc/library/create', { title: $('#bookTitle').val() })
            .done(html => $('#bookResult').html(html));
    });
    $('#listBooksBtn').click(() => {
        $.get('/mvc/library/list')
            .done(html => $('#bookResult').html(html));
    });
});