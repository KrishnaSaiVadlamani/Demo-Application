from flask import Flask,Response
from flask_restful import Resource,Api,reqparse
import logging
import sys

class request(Resource):

    parser=reqparse.RequestParser()

    parser.add_argument("title", type=str,required=True, help="not be empty")
    parser.add_argument("body", type=str,required=True, help="not be empty")

    def post(self):
        data=request.parser.parse_args()
        logging.debug('Received request')
        logging.debug("Received Notificaton : "+str(data['title']+" "+data['body']))

        return Response(status=200)
 