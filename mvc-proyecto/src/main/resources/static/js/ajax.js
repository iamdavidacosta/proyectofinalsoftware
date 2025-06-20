$(function() {
    // Cálculo Científico
    $('#multBtn').click(() => {
        const a = $('#a').val();
        const b = $('#b').val();
        $.post('/mvc/calc/matrix', { a, b })
            .done(html => $('#multResult').html(html));
    });
    $('#intBtn').click(() => {
        $.post('/mvc/calc/integrate', $('#calcForm').serialize())
            .done(html => $('#intResult').html(html));
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