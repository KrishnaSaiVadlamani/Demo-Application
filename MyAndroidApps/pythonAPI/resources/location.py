import logging

from flask import request, Response
from flask_restful import Resource

from util.storage import handle_report_location_storage


class Location(Resource):

    def post(self):
        try:
            logging.debug("Handle Report Location Request Started")
            data = request.json
            handle_report_location_storage(data)
            logging.debug("Handle Report Location Request Done")
        except Exception as e:
            logging.error("Failed to handle Report Location", e)
        return Response(status=200)
