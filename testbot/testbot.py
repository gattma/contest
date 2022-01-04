from csv import reader, writer
from pathlib import Path
from datetime import datetime
import requests
import time

BASE_URL = "https://solr-search-service-contest.apps.play.gepaplexx.com"


def dict_to_list(dictpath):
    with open(dictpath, 'r') as read_obj:
        csv_reader = reader(read_obj)
        list_of_rows = list(csv_reader)
    return list_of_rows


def run_tests(words):
    Path("output").mkdir(parents=True, exist_ok=True)
    with open(f"output/result-{datetime.now().strftime('%d-%b-%Y_%H-%M-%S')}", "w+", encoding="UTF8") as f:
        w = writer(f, delimiter=";")
        w.writerow(["word", "elapsed time", "hits", "response"])
        for word in words:
            start = time.time()
            response = requests.get(f"{BASE_URL}/findByText?text={word[0]}")
            end = time.time()
            w.writerow([word[0], end - start, len(response.json()), response.json()])


def run():
    dictionary = "input/dict_short.csv"
    words = dict_to_list(dictionary)
    run_tests(words)


if __name__ == '__main__':
    run()
