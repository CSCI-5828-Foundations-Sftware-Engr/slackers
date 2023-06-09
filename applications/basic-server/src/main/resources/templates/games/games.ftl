<#-- @ftlvariable name="games" -->
<#import "../_layout.ftl" as layout />
<@layout.header>
    <h1 class="my-4 text-5xl m-5 text-center leading-none tracking-tight md:text-5xl lg:text-6xl text-[#a58c4a]" style="font-family: 'Ultra', serif;">Games</h1>
    <hr>
<div class="grid grid-cols-2 gap-4 h-[100%] p-">
    <#list games as game>
    <div class="w-full flex items-center justify-center">
        <a class="font-medium text-blue-600 dark:text-blue-500 hover:underline" href="/game/${game.id}">
        <div
          class="flex flex-col w-[20rem] rounded-lg bg-[#cdc9cb] shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)] m-5 p-3 dark:bg-neutral-700 md:max-w-xl md:flex-row">
              <div class="game flex flex-col justify-start p-6">
                <h5
                  class="mb-2 text-xl font-medium text-neutral-800 dark:text-neutral-50">
                  <a class="game-name font-medium text-[#ce5936] dark:text-blue-500 hover:underline" href="/game/${game.id}">Game: ${game.name} </a>
                </h5>
                <p class="game-description mb-4 text-base text-neutral-600 dark:text-neutral-200 overflow-auto h-[8rem] scrollbar-hide">
                  ${game.description}
                </p>
              </div>
            </div>
          </a>
    </div>
    </#list>
</div>
<div class="flex flex-col items-center">
    <a href="/games/new" class="add-game bg-[#cdc9cb] m-3 hover:bg-[#ce5936] text-[#a58c4a] font-bold py-2 px-4 border border-[#ce5936] rounded">
      Add Game
    </a>
</div>








</@layout.header>