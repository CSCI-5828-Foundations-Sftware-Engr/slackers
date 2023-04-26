<#-- @ftlvariable name="sessions" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>
            Sessions
        </h3>
        <hr />
        <div>

            <#list sessions as session>
                <a href="/sessions/${session.sessionId}">
                    <div>
                        ${session.sessionName}
                        Players:
                        <#list session.players as player>
                            ${player.name}
                        </#list>
                    </div>
                </a>
            </#list>
        </div>
        <div class="flex flex-col items-center">
            <a href="/createSession" class="bg-[#cdc9cb] m-3 hover:bg-[#ce5936] text-[#a58c4a] font-bold py-2 px-4 border border-[#ce5936] rounded">
              Create Session
            </a>
        </div>

    </div>
</@layout.header>