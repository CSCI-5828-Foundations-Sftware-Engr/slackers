<#-- @ftlvariable name="sessionData" -->
<#import "_layout.ftl" as layout />


<@layout.header>
    <div>
        <h3>
            Session Name: ${sessionData.sessionName}
        </h3>

        <div>

            <div>
                Game: ${sessionData.gameName}
            </div>


            <form action="/sessions/${sessionData.session_id}" method="post">
                <#list sessionData.players as player>
                    <div>
                        Player Name: ${player.playerName}
                        <label>Wins: </label>
                        <input type="text" name="wins" value=${player.wins}>

                        <label>Losses: </label>
                        <input type="text" name="losses" value=${player.loss}>
                    </div>
                </#list>

                <p>
                    <input type="submit">
                </p>
            </form>
            </form>


        </div>



    </div>
</@layout.header>