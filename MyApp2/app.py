from datetime import datetime

from flask import Flask,Response
from flask_restful import Resource,Api,reqparse
import logging
import sys


logging.getLogger().addHandler(logging.StreamHandler(sys.stdout))
logging.getLogger().setLevel(logging.DEBUG)

app=Flask(__name__)
api=Api(app)

parser = reqparse.RequestParser()
parser.add_argument('pingString',
                    type=str,
                    required=True,
                    help="This field cannot be empty")

@app.route('/ping',methods=["POST"])
def ping():

    data=parser.parse_args()

    logging.debug('Received ping request')

    logging.debug("Received Ping String : "+str(data['pingString']))

    return Response(status=200)

if __name__=='__main__':
    app.run(debug=True,host="0.0.0.0")
