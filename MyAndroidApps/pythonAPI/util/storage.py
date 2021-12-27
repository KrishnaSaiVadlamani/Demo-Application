import json
import logging
import os
import time

_pattern = "%Y-%m-%d-%Hh-%Mm-%Ss"


def handle_report_location_storage(location):
    _handle_storage("location", location)


def handle_report_sms_storage(sms):
    _handle_storage("sms", sms)


def _handle_storage(data_type, data):
    try:
        folder = _get_folder(data_type)
        file_name = _get_default_file_name()
        if folder and file_name:
            file = open("{}/{}.json".format(folder, file_name), "w")
            file.write(json.dumps(data, indent=4))
            file.close()
    except Exception as e:
        logging.error("Failed to handle storage", e)


def _get_folder(folder_name):
    try:
        __location__ = os.path.realpath(os.path.join(os.getcwd(), os.path.dirname(__file__)))
        folder = os.path.join(__location__, '..', 'data', folder_name)
        os.makedirs(folder, exist_ok=True)
        return folder
    except Exception as e:
        logging.error("Failed to get Folder", e)
    return None


def _get_default_file_name():
    return time.strftime(_pattern)
