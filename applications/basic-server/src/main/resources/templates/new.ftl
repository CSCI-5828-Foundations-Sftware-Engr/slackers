<#import "_layout.ftl" as layout />
<@layout.header>
<div>
<h3>What is your name?</h3>
<form action="/form" method="post">
            <p>
                <input type="text" name="title">
            </p>
            <p>
                <input type="submit">
            </p>
        </form>
    </div>
</@layout.header>