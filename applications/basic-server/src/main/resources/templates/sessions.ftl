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
                <a href="/sessions/${session.session_id}">
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

    </div>
</@layout.header>