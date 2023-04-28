<#-- @ftlvariable name="gameNewsData" -->
<#import "../_layout.ftl" as layout />
<@layout.header>
    <div>
        <h1 class="my-4 text-5xl m-5 text-center leading-none tracking-tight md:text-5xl lg:text-6xl text-[#a58c4a] game-name" style="font-family: 'Ultra', serif;">${gameNewsData.name}</h1>
        <hr>
        <div class="mb-4 text-[#cdc9cb] px-4 py-5">
            <p>
                ${gameNewsData.description}
            </p>
        </div>
        <hr />

        <h3 class="text-center text-[#cdc9cb] font-bold text-2xl p-4">
            Recent News
        </h3>
        <div class="flex flex-col">
            <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
                <div class="inline-block min-w-full py-2 sm:px-6 lg:px-8">
                    <div class="overflow-hidden">
                        <table class="min-w-full text-center text-sm font-light">
                            <thead class="border-b font-medium dark:border-neutral-500">
                            <tr>
                                <th scope="col" class="px-6 py-4">News</th>
                                <th scope="col" class="px-6 py-4">Description</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list gameNewsData.news as n>

                                <tr id="table-news" class="border-b transition duration-300 ease-in-out hover:bg-[#cdc9cb] dark:border-neutral-500 dark:hover:bg-neutral-600">
                                    <td class="whitespace-nowrap px-6 py-4 font-medium"><a href=${n.url} target="_blank" class="font-medium hover:underline">${n.title}</a></td>
                                    <td class="whitespace-nowrap px-6 py-4 font-medium">${n.description}</td>

                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@layout.header>