<#-- @ftlvariable name="playerGameOptions" -->
<#import "../_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3 class="text-center text-[#cdc9cb] font-bold text-2xl p-4">
            Create Session
        </h3>
        <hr />
        <div class=" flex items-center justify-center">
            <form action="/sessions" method="post">

                <div class="flex items-center border-b py-2">
                    <input class="appearance-none bg-transparent border-none w-full text-gray-700 mr-3 py-1 px-2 leading-tight focus:outline-none" placeholder="Session Name" type="text" name="sessionName">
                </div>




                <div class="mb-[0.125rem] block min-h-[1.5rem]">
                    <h1 class="mb-2">Select Players</h1>
                    <#list playerGameOptions.players as player>
                        <div class="flex mb-4">
                            <input class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600" name="${player.name}" type="checkbox">
                            <label class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">${player.name}</label>
                        </div>
                    </#list>
                    <h1 class="mb-2">Choose Games</h1>
                    <#list playerGameOptions.games as game>
                        <div class="flex mb-4">
                            <input class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600" type="radio" name="game" value=${game.id}>
                            <label class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">${game.name}</label>
                        </div>
                    </#list>
                </div>


                <input class="bg-[#cdc9cb] m-3 hover:bg-[#ce5936] text-[#a58c4a] font-bold py-2 px-4 border border-[#ce5936] rounded" type="submit">

            </form>
        </div>
    </div>
</@layout.header>