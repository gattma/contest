import json


JSON_FILE = "/Users/markogattringer/Documents/logdaten/2021_{0}_{1:02d}.json"


def run():
    result = open('/Users/markogattringer/Documents/logdaten/result.txt', 'a')

    # NOV
    for day in range(8, 31):
        file = JSON_FILE.format(11, day)
        print(file)
        with open(file) as f:
            d = json.load(f)
            for hit in d["responses"][0]["hits"]["hits"]:
                result.write(f"{hit['_source']['message']}\n")

    # DEZ
    for day in range(1, 8):
        file = JSON_FILE.format(12, day)
        print(file)
        with open(file) as f:
            d = json.load(f)
            for hit in d["responses"][0]["hits"]["hits"]:
                result.write(f"{hit['_source']['message']}\n")

    result.close()

    #with open(JSON_FILE) as f:
    #    d = json.load(f)
    #    for hit in d["responses"][0]["hits"]["hits"]:
    #        print(hit["_source"]["message"])


if __name__ == '__main__':
    run()
