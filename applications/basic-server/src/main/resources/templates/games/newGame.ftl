<#-- @ftlvariable name="game" -->
<#import "../_layout.ftl" as layout />
<@layout.header>
    <div>
        <h1 class="text-4xl text-center mb-4 text-[#cdc9cb]">
            Create a new session for ${game.name}
        </h1>
        <hr>

        <div class="mb-4 text-[#cdc9cb] px-4 py-5">
            <h1>TODO WORK create the form for creating a session</h1>
            <form class="w-full max-w-sm border-solid border-1 p-5">
                <div class="flex items-stretch">
                  <div class="relative z-0 w-full mb-6 group">
                      <input autocomplete="off" type="username" name="username" id="username" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
                      <label for="username" class="peer-focus:font-medium absolute text-2xl text-white dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-1 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#a58c4a] peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Username</label>
                  </div>
                    <div>
                        <button class="bg-[#cdc9cb] mx-2 hover:bg-[#ce5936] text-[#a58c4a] p-2 font-bold px-4 border border-[#ce5936] rounded" href="#">
                            Add
                        </button>
                    </div>
                </div>
            </form>
        </div>



    </div>
</@layout.header>