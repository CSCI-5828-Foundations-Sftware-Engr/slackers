<#import "navbar.ftl" as navbar />
<#import "footer.ftl" as footer />
<#macro header>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Team Slackers</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Slabo+27px&family=Ultra&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>



    <body class="bg-[#161f36] mx-auto">

        <@navbar.header></@navbar.header>

        <h1 class="my-4 text-5xl m-5 text-center leading-none tracking-tight md:text-5xl lg:text-6xl text-[#a58c4a]" style="font-family: 'Ultra', serif;">GOODBOARDS</h1>

        <div class="container rounded-md bg-slate-500  mx-auto">
            <#nested>
        </div>

    <@footer.footer></@footer.footer>
    </body>

    </html>
</#macro>