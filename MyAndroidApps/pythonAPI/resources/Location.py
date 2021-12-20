from flask import Flask,Response
from flask_restful import Resource, reqparse

import logging
import json


class location(Resource):

    parser=reqparse.RequestParser()
    parser.add_argument("Latitude", type=str,required=True, help="not be empty")
    parser.add_argument("Longitude", type=str,required=True, help="not be empty")                    
   
    def post(self):

        data=location.parser.parse_args()

     
        logging.debug("Received Location : "+str(data['Latitude'])+" "+data['Longitude'])


        with open("LocationOutput.json", "r+") as outfile:
            file_data=json.load(outfile)
            file_data["dimensions"].append(data)
            outfile.seek(0)
            json.dump(file_data,outfile,indent=4)

        

        return {"status":"200"}
