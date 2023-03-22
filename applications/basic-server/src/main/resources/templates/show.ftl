<#-- @ftlvariable name="article" type="com.example.models.Article" -->
<#import "_layout.ftl" as layout />
<@layout.header>
<div>
<h3>
${user.title}
</h3>
<hr>
<p>
<a href="/form/${user.id}/edit">Edit Name</a>
        </p>
    </div>
</@layout.header>