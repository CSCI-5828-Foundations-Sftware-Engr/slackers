<#-- @ftlvariable name="users" type="kotlin.collections.List<com.example.models.Users>" -->
<#import "_layout.ftl" as layout />
<@layout.header>
<#list users?reverse as user>
<div>
<h3>
<a href="/form/${user.id}">${user.title}</a>
            </h3>
        </div>
    </#list>
    <hr>
    <p>
        <a href="/form/new">Create name</a>
    </p>
</@layout.header>