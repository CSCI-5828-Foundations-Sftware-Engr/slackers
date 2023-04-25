<#-- @ftlvariable name="sessionData" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>
            ${sessionData.sessionName}
        </h3>

        <div>
            SessionID: ${sessionData.session_id}
            GameID: ${sessionData.game_id}
            <#list sessionData.players as player>
                Player Name: ${player.playerId}
                Wins: ${player.wins}
                Losses: ${player.loss}
            </#list>
        </div>



    </div>
</@layout.header>