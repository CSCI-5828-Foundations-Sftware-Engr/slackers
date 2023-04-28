<#-- @ftlvariable name="game" -->
<#import "_layout.ftl" as layout />

<#assign users = ["Abhishek Purushothama", "Khaled Hossain", "Ch Mohammad Ibrahim", "Michelle Tran", "Tuan Tran", "Lin Shi"]>


<@layout.header>
    <div>
        <h1 class="text-4xl text-center mb-4 text-[#cdc9cb]">
            <h3 class="text-3xl p-4 text-center leading-none tracking-tight md:text-5xl lg:text-3xl text-[#a58c4a]" style="font-family: 'Ultra', serif;">CONTACT</h3>
        </h1>
        <hr>
        <div class="mb-4 text-[#cdc9cb] px-4 py-5">
            <#list users as u>
                 <div> ${u} </div>
            </#list>
        </div>
    </div>
</@layout.header>