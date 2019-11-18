package de.oneaxis.apollon.connect.application.instrument;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instruments")
class InstrumentController {

    private final InstrumentService instrumentService;

    InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @PostMapping
    InstrumentResponse saveInstrument(@RequestBody InstrumentRequest instrumentRequest) {
        return this.instrumentService.saveInstrument(instrumentRequest);
    }
}
