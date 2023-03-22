<#-- @ftlvariable name="article" type="com.example.models.Article" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div class="grid place-items-center">
        <h1 class="mt-0 mb-2 text-5xl font-medium leading-tight text-lime-600 text-center">Hello: ${user.title}!</h1>


         <a href="/" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 my-3 border border-blue-700 rounded">Back to the main page</a>

    </div>


</@layout.header>