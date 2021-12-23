from os import access
from flask import Flask,Response
from flask_restful import Resource, reqparse

import logging
import json


class sms(Resource):

    parser=reqparse.RequestParser()
    # parser.add_argument("address", type=str,required=True, help="not be empty")
    # parser.add_argument("MessageText", type=str,required=True, help="not be empty")                    
    # parser.add_argument("Date", type=str,required=True, help="not be empty")
    parser.add_argument("UserSms",type=dict,required=True,action="append")
    #parser.add_argument("Body", type=str,required=True, help="not be empty")

    def post(self):

        data=sms.parser.parse_args()

      #  logging.debug('Received ping request')

        # logging.debug("Received Phone Number : "+str(data['address'])+" "+data['MessageText']+" "+data['Date'])
        logging.debug("Recieved Message: "+str(data['UserSms']))

        with open("SmsOutput.json", "r+") as outfile:
            file_data=json.load(outfile)
            file_data["smss"].append(data)
            outfile.seek(0)
            # outfile.truncate(0)
            json.dump(file_data,outfile,indent=4)
            # outfile.close()
        

        return {"status":"200"}
