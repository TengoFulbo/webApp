
<!DOCTYPE html>
<html lang="es">
    
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <!-- <title>TursistaUY</title> -->
        <title>
            <%
                String pageTitle = (String) request.getAttribute("pageTitle");
                if (pageTitle == null) {
                    out.print("TurismoUY");
                } else {
                    out.print(pageTitle);
                }
            %>
        </title>

    <!-- LINKS DE CSS -->
    <link rel="stylesheet" href="./src/css/homeStyle.css" />

    <!-- GOOGLE FONTS -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script&family=Hind&family=Montserrat&display=swap"
        rel="stylesheet" />

    <!-- MATERIALIZE -->
    <link rel="stylesheet" href="src/css/materialize.css" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
</head>