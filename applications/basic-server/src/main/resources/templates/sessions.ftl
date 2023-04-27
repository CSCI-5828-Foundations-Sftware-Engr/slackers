<#-- @ftlvariable name="sessions" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3 class="text-center text-[#cdc9cb] font-bold text-2xl p-4">
            SESSIONS
        </h3>
        <hr />
        <div class="flex items-center justify-center">

            <div class="flex flex-col">
                <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
                    <div class="inline-block min-w-full py-2 sm:px-6 lg:px-8">
                        <div class="overflow-hidden">
                            <table class="min-w-full text-left text-sm font-light">
                                <thead class="border-b font-medium dark:border-neutral-500">
                                <tr>
                                    <th scope="col" class="px-6 py-4">Session Id</th>
                                    <th scope="col" class="px-6 py-4">Session Name</th>
                                    <th scope="col" class="px-6 py-4">Players</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list sessions as session>

                                        <tr
                                                class="border-b transition duration-300 ease-in-out hover:bg-[#cdc9cb] dark:border-neutral-500 dark:hover:bg-neutral-600">
                                            <td class="whitespace-nowrap px-6 py-4 font-medium">${session.sessionId}</td>
                                            <td class="whitespace-nowrap px-6 py-4"> <a href="/sessions/${session.sessionId}">${session.sessionName} </a></td>
                                            <td class="whitespace-nowrap px-6 py-4">
                                                <#list session.players as player>
                                                    ${player.name}
                                                </#list>
                                            </td>
                                        </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="flex flex-col items-center">
            <a href="/createSession" class="bg-[#cdc9cb] m-3 hover:bg-[#ce5936] text-[#a58c4a] font-bold py-2 px-4 border border-[#ce5936] rounded">
              Create Session
            </a>
        </div>

    </div>
</@layout.header>