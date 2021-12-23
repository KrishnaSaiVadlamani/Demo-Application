from flask import Flask
from flask_restful import Api
from resources.Location import location
from resources.Request import request

from resources.Sms import sms
import logging
import sys

logging.getLogger().addHandler(logging.StreamHandler(sys.stdout))
logging.getLogger().setLevel(logging.DEBUG)


app = Flask(__name__)

api = Api(app)

api.add_resource(sms, '/sms')
api.add_resource(location,'/location')
api.add_resource(request,'/notification')
if(__name__ == '__main__'):
    app.run(port=5000, debug=True, host='0.0.0.0')
