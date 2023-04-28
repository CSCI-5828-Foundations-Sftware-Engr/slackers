<#-- @ftlvariable name="sessionData" -->
<#import "_layout.ftl" as layout />


<@layout.header>
    <div>
        <h3 class="text-center text-[#cdc9cb] border border-b font-bold text-2xl p-4">
            ${sessionData.sessionName}
        </h3>
        <h4 class="text-center p-3">Playing: ${sessionData.gameName}</h4>
        <div class=" flex items-center justify-center">
            <form action="/sessions/${sessionData.session_id}" method="post">

                <div class="flex flex-col">
                    <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
                        <div class="inline-block min-w-full py-2 sm:px-6 lg:px-8">
                            <div class="overflow-hidden">
                                <table class="min-w-full text-left text-sm font-light">
                                    <thead class="border-b font-medium dark:border-neutral-500">
                                    <tr>
                                        <th scope="col" class="px-6 py-4">Player Name</th>
                                        <th scope="col" class="px-6 py-4">Wins</th>
                                        <th scope="col" class="px-6 py-4">Losses</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list sessionData.players as player>
                                        <tr
                                                class="border-b">
                                            <td class="whitespace-nowrap px-6 py-4 font-medium">${player.playerName}</td>
                                            <td class="whitespace-nowrap px-6 py-4"><input type="text" name="wins" value=${player.wins}></td>
                                            <td class="whitespace-nowrap px-6 py-4"><input type="text" name="losses" value=${player.loss}></td>
                                        </tr>
                                    </#list>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <input class="bg-[#cdc9cb] m-3 hover:bg-[#ce5936] text-[#a58c4a] font-bold py-2 px-4 border border-[#ce5936] rounded" type="submit">

            </form>


        </div>



    </div>
</@layout.header>