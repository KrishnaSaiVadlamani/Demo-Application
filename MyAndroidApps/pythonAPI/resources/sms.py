from flask import request, Response
from flask_restful import Resource

from util.storage import handle_report_sms_storage

import logging


class Sms(Resource):

    def post(self):
        try:
            logging.debug("Handling Report SMS request started")
            data = request.json
            handle_report_sms_storage(data)
            logging.debug("Handling Report SMS request done")
        except Exception as e:
            logging.error("Failed to handle report sms request", e)
        return Response(status=200)
