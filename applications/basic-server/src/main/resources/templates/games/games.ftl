<#-- @ftlvariable name="games" -->
<#import "../_layout.ftl" as layout />

<@layout.header>
<div class="flex flex-col">
    <h1 class="text-3xl p-4 text-center leading-none tracking-tight md:text-5xl lg:text-3xl text-[#a58c4a]" style="font-family: 'Ultra', serif;">Games</h1>
    <hr />
    <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
        <div class="inline-block min-w-full py-2 sm:px-6 lg:px-8">
            <div class="overflow-hidden">
                <table class="min-w-full text-center text-sm font-light">
                    <thead class="border-b font-medium dark:border-neutral-500">
                    <tr>
                        <th scope="col" class="px-6 py-4">Game</th>
                        <th scope="col" class="px-6 py-4">Description</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list games as game>
                        <tr id="table-news" class="border-b transition duration-300 ease-in-out hover:bg-[#cdc9cb] dark:border-neutral-500 dark:hover:bg-neutral-600">
                            <td class="whitespace-nowrap px-6 py-4 font-medium"><a href="/game/${game.id}" class="font-medium hover:underline">${game.name}</a></td>
                            <td class="whitespace-nowrap px-6 py-4 font-medium"><a href="/game/${game.id}" class="font-medium hover:underline">${game.description}</a></td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="flex flex-col items-center">
    <button class="bg-[#cdc9cb] m-3 hover:bg-[#ce5936] text-[#a58c4a] font-bold py-2 px-4 border border-[#ce5936] rounded">
      Add Game
    </button>
</div>








</@layout.header>