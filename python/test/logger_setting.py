import logging
import os

directory = "./log/"
if not os.path.exists(directory):
    os.makedirs(directory)

# Formato da resposta
format = logging.Formatter("%(asctime)s - [%(levelname)s] %(message)s")


# Logger para mensagens de INFO
info_logger = logging.getLogger("info_logger")
info_logger.setLevel(logging.INFO)

info_handler = logging.FileHandler("./log/info.log", mode="w")
info_handler.setLevel(logging.INFO)
info_handler.setFormatter(format)

info_logger.addHandler(info_handler)


# Logger para mensagens de DEBUG
debug_logger = logging.getLogger("debug_logger")
debug_logger.setLevel(logging.DEBUG)

debug_handler = logging.FileHandler("./log/debug.log", mode="w")
debug_handler.setFormatter(format)

debug_logger.addHandler(debug_handler)


# Logger para mensagens de warning
warning_logger = logging.getLogger("warning_logger")
warning_logger.setLevel(logging.WARNING)

warning_handler = logging.FileHandler("./log/warning.log", mode="w")
warning_handler.setFormatter(format)

warning_logger.addHandler(warning_handler)


# Logger para mensagens de error
error_logger = logging.getLogger("error_logger")
error_logger.setLevel(logging.ERROR)

error_handler = logging.FileHandler("./log/error.log", mode="w")
error_handler.setFormatter(format)

error_logger.addHandler(error_handler)


selenium_logger = logging.getLogger('selenium')
selenium_logger.setLevel(logging.CRITICAL) # Ignora todos os logs do Selenium

def info(message):
    info_logger.info(message)

def debug(message):
    debug_logger.debug(message)

def warning(message):
    warning_logger.warning(message)

def error(message):
    error_logger.error(message)
