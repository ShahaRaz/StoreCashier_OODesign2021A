#!/usr/bin/python


import subprocess

from datetime import datetime

from flask import Flask, request

app = Flask(__name__)
COMPILATION_DIR = '/Users/SRazStudent/git/Airport2020B_JAVA/AirportDevTools2020B/src'

RUNNABLE_DIRECTORY = "/Users/SRazStudent/git/Airport2020B_JAVA/AirportDevTools2020B/bin"

terminalCompile = '-d build -cp ./jars/*.jar *.java'
terminalRun = 'java -cp ./build:./jars/AirportDev14July.jar AirportActivition'

proc = subprocess.Popen(terminalCompile, shell=True, cwd=COMPILATION_DIR)

#constants
DEPARTURES = 'departures'
ARRIVALS = 'arrivals'




# subprocess.check_call()
# // Args:
# 	// 0 - User Interface kind
# 	// 1 - departures or Arrivials
# 	// 2 - airline brand
# 	// 3 - country
# 	// 4 - City
# 	// 5 - Airport
# 	// 6 - starting date
# 	// 7 - ending date
# 	// 8 - week days [ 1= Sunday , 2=Monday... 7=Saturday]

@app.route("/check")
def printCheck():
    # print("hello") # not working
    return 'hello'



@app.route("/")
def printHeaderHtml():
        mainPageHtml="""         
      <h1 style="text-align: center;"><strong> <a href="arrivals?outFormat=html"> arrivals </a> OR <a href="departures?outFormat=html"> departures </a> </strong></h1>
        """
        return mainPageHtml







def setHtmlPage(s,direction):
    htmlSplit1 = " <form action ="+direction
    htmlInput= """ " method="GET">
    <form action="departures " method="GET">
    <div style="text-align: center;">Format: HTML <input checked="checked" name="outFormat" type="radio" value="html" /> console <input name="outFormat" type="radio" value="console" /></div>
    <h3 style="text-align: center;"><strong><label for="airline"> Airline </label></strong><select name="airline">
    <option value="">No restrictions</option>
    <option value="Arkia">Arkia</option>
    <option value="El-Al">El-Al</option>
    <option value="Israir">Israir</option>
    <option value="Wizz ">Wizz</option>
    <option value="Turkish Airlines">Turkish Airlines</option>
    <option value="American Airlines">American Airlines</option>
    </select></h3>
    <div style="text-align: center;"><strong><label for="country"> Country </label></strong><select name="country">
    <option value="">No restrictions</option>
    <option value="Spain ">Spain</option>
    <option value="France ">France</option>
    <option value="Ukrain ">Ukrain</option>
    <option value="Belguim ">Belguim</option>
    <option value="UK">UK</option>
    <option value="China ">China</option>
    <option value="Ireland ">Ireland</option>
    <option value="Austria ">Austria</option>
    <option value="Germany ">Germany</option>
    <option value="Czech Republic ">Czech Republic</option>
    <option value="Kenya ">Kenya</option>
    <option value="Japan ">Japan</option>
    <option value="India ">India</option>
    <option value="Croatia ">Croatia</option>
    <option value="Norway ">Norway</option>
    <option value="Latvia ">Latvia</option>
    <option value="USA">USA</option>
    <option value="Finland ">Finland</option>
    <option value="Russia ">Russia</option>
    </select><em><label for="city"> City </label></em> <input name="city" type="text" value="" /> <em><label for="airport"> Airport </label></em> <input name="airport" type="text" value="" /></div>
    <div style="text-align: center;">&nbsp;</div>
    <div style="text-align: center;">&nbsp;</div>
    <div style="text-align: center;"><em><strong><label for="weekdays"> weekdays </label></strong></em> <input max="7654321" min="1" name="weekDays" size="40" step="1" type="number" placeholder="127=Sunday,Monday,Saturday " />
    <div style="text-align: center;">&nbsp;</div>
    <div style="text-align: center;">&nbsp;</div>
    <div><strong><label for="startDate"> Starting Date </label> <input name="startingDate" type="date" value="" /> <label for="endDate"> To Date </label> <input name="endingDate" type="date" value="" /></strong></div>
    <button type="reset"> Reset </button> <button type="submit"> Submit </button></div>
    <div style="text-align: center;"><a href="/"> Change direction </a></div>
    </form>
    """

    htmlInput = htmlSplit1+htmlInput
    s = bytes.decode(s)

    return htmlInput+s


# @app.route("/")
# def general():
#     javaResult =  (subprocess.check_output(["java",
#                                  "boardActivition/AirportActivation",
#                                  request.args.get('outFormat', ''), request.args.get('direction', 'incoming'),  # args[0] + args[1]
#                                  request.args.get('airline', ''), request.args.get('country', ''),  # args[2] + args[3]
#                                  request.args.get('city', ''), request.args.get('airport', ''),  # args[4] + args[5]
#                                  request.args.get('startingDate', ''), request.args.get('endingDate', ''),# args[6] + args[7]
#                                  request.args.get('weekDays', '')],  # args[8]
#                                  cwd=RUNNABLE_DIRECTORY))
#
#     return setHtmlPage(javaResult)




@app.route("/departures")
def dep():
    javaResult =  (subprocess.check_output(["java",
                                 "boardActivition/AirportActivation",
                                 request.args.get('outFormat', 'html'), DEPARTURES,  # args[0] + args[1]
                                 request.args.get('airline', ''), request.args.get('country', ''),  # args[2] + args[3]
                                 request.args.get('city', ''), request.args.get('airport', ''),  # args[4] + args[5]
                                 request.args.get('startingDate', ''), request.args.get('endingDate', ''),
                                 # args[6] + args[7]
                                 request.args.get('weekDays', '')],  # args[8]
                                 cwd=RUNNABLE_DIRECTORY))

    return setHtmlPage(javaResult,DEPARTURES)




@app.route("/arrivals")
def arr():
    javaResult = (subprocess.check_output(["java",
                                           "boardActivition/AirportActivation",
                                           request.args.get('outFormat', 'html'), ARRIVALS,  # args[0] + args[1]
                                           request.args.get('airline', ''), request.args.get('country', ''),
                                           # args[2] + args[3]
                                           request.args.get('city', ''), request.args.get('airport', ''),
                                           # args[4] + args[5]
                                           request.args.get('startingDate', ''), request.args.get('endingDate', ''),
                                           # args[6] + args[7]
                                           request.args.get('weekDays', '')],  # args[8]
                                          cwd=RUNNABLE_DIRECTORY))

    return setHtmlPage(javaResult, ARRIVALS)


app.run(port=8000, host="0.0.0.0")






#___________________________________________________


# Stages to run this code in the terminal:

# a. create directories for JARS and Builds (mkdir JARS , mkdir build)
# b. go to eclipse & configure classpath for project, set main as the Board (not Program)
# c. export the project as jar file to the JARS folder we made in a
# d. (for future using of the code) configure classpath back to Program.
##
# 1. build path with the jar files in place (in order to detect dependencis and libraries etc )  for compilatio
# this stage will build alot of files in the "build" folder
# CODE:      javac -d build -cp ./jars/*.jar *.java
# javaC - the C stands for compilation of the java code.
# the -d do???

# 2. run the
# CODE:      java -cp ./build:./jars/AirportDev14July.jar AirPortBoard
# running the AirPortBoard class using the AirportDev14July.jar file to know dependencies




#___________________________________________________

##HTML Editing website:
# https://html-online.com/editor/
#  Table String  also from https://html5-editor.net


#_____________________________________________________
# type : kind of information to be entered - ex : text , number , password, email
# name : the name of the attribute ( will be used in the next page )_
# id : so other objects in the page would be able to refer to the specific one
# label : a label of plane text
# input : a place to put in the text
# value  : default value for the input
# placeholder: like hint in (greyed out text on the field)
# required : mention that the field cannot be submitted empty

