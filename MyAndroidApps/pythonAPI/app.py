from flask import Flask
from flask_restful import Api

from resources.location import Location
from resources.sms import Sms

import logging

log_format = ' %(asctime)s - %(levelname)s - %(message)s'
logging.basicConfig(format=log_format)
logging.getLogger().setLevel(logging.DEBUG)

app = Flask(__name__)
api = Api(app)

api.add_resource(Sms, '/sms')
api.add_resource(Location, '/location')

if __name__ == '__main__':
    app.run(port=1324, host='0.0.0.0')
